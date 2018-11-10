------------------------------------------------------------------------
---
--- This script allows skin to calculate difference between some values
--- that are separated in time for N updates.
---
--- Options: { name : type : default }
---   HistWidth : int : default -1
---     Specifies gap between values (in updates).
---     Must be > 0.
---   CurValue : float : default 0
---     Numerical value to process.
---
--- Return value: float
--- Delta between current value and value that is HistWidth in past.
---
---
--- Bangs: { name and args }
---   Reset()
---     Sets all remembered values to current value
---     so that if none changes happen then dalta is 0.
---
---
--- Reset() is called on script load  so that all values are set to 0.
--- As on script load measures are likely to have value "0"
--- if tou set CurValue to some measure then on next update
--- delta will be 100% value of this measure.
--- To avoid it you can call Reset() manually before first update.
--- For example, in "IfTrueAction" option: these are executed
--- when everything is initialized.
---
------------------------------------------------------------------------

local tValues = {}
local nHistWidth = 0

local nCurrentIndex = 0
local bIsFirstUpdate = true
local nFirstValue = 0
local bBroken = false

function Initialize()
    Reset()

    return
end

--- Sets all saved values to current value and reset all variables to their initial values.
function Reset()
    nHistWidth = SELF:GetNumberOption('HistWidth', nHistWidth)
    if (nHistWidth <= 0) then
        SKIN:Bang('!Log', 'HistWidth=' .. SELF:GetOption('HistWidth').. ' is not valid. Aborting', 'Error')
        bBroken = true
    end

    nFirstValue = SELF:GetNumberOption('CurValue', 0)
    -- All nils are replaced with nFirstValue
    tValues = {}

    nCurrentIndex = 0

    bIsFirstUpdate = true
end

function Update()
    if (bBroken) then
        return 'broken'
    end

    local nValue = SELF:GetNumberOption('CurValue', 0)

    nCurrentIndex = nCurrentIndex + 1
    if (nCurrentIndex > nHistWidth) then
        nCurrentIndex = 1
    end

    local nDelta = 0
    if (bIsFirstUpdate) then
        bIsFirstUpdate = false
    else
        nDelta = nValue - (tValues[nCurrentIndex] or nFirstValue)
    end
    tValues[nCurrentIndex] = nValue

    return nDelta
end
